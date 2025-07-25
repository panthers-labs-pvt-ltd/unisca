package org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query;

import org.pantherslabs.chimera.unisca.api_nexus.api_nexus_client.dynamic_query.dto.FilterCondition;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class GenericSqlProvider {


    public String buildQuery(Map<String, Object> params) {
        String table = (String) params.get("table");
        String SQL = "";

        if (table != null) {
            String trimmed = table.trim().toLowerCase();
            if (trimmed.startsWith("select") || trimmed.startsWith("with")) {
                return table;
            } else {
                SQL = String.format("SELECT * FROM %s  WHERE 1=1", table);
            }
        }

        StringBuilder sql = new StringBuilder(SQL);
        @SuppressWarnings("unchecked")
        List<FilterCondition> filters =
                (List<FilterCondition>) params.get("filters");
        sql.append(buildWhereClause(filters, "filters", /*nested=*/false));
        return sql.toString();
    }

    private String buildWhereClause(List<FilterCondition> filters, String pathPrefix, boolean nested) {
        StringBuilder clause = new StringBuilder();

        for (int i = 0; i < filters.size(); i++) {
            FilterCondition f = filters.get(i);
            String logic = f.getLogic() == null ? "AND" : f.getLogic().toUpperCase();
            String currentPath = pathPrefix + "[" + i + "]";
            String paramName = currentPath + ".value";

            if (i == 0) {
                clause.append(nested ? " " : " AND ");
            } else {
                clause.append(" ").append(logic).append(" ");
            }

            if (f.isGroup()) {
                clause.append("(")
                        .append(buildWhereClause(f.getGroup(), currentPath + ".group", true))
                        .append(")");
                continue;
            }

            String op = f.getOperator().toLowerCase();
            clause.append(f.getField()).append(" ");

            switch (op) {
                case "is null":
                case "is not null":
                    clause.append(op.toUpperCase());
                    break;
                case "like":
                    clause.append("LIKE CONCAT('%', #{").append(paramName).append("[0]}, '%')");
                    break;
                case "not like":
                    clause.append("NOT LIKE CONCAT('%', #{").append(paramName).append("[0]}, '%')");
                    break;
                case "between":
                    clause.append("BETWEEN #{").append(paramName).append("[0]} AND #{")
                            .append(paramName).append("[1]}");
                    break;
                case "in":
                    clause.append("IN ")
                            .append("<foreach collection=\"").append(paramName)
                            .append("\" item=\"item\" open=\"(\" separator=\",\" close=\")\">")
                            .append("#{item}</foreach>");
                    break;
                case "not in":
                    clause.append("NOT IN ")
                            .append("<foreach collection=\"").append(paramName)
                            .append("\" item=\"item\" open=\"(\" separator=\",\" close=\")\">")
                            .append("#{item}</foreach>");
                    break;
                case "=": case ">": case "<": case ">=": case "<=": case "!=": case "<>":
                    clause.append(op).append(" #{").append(paramName).append("[0]}");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + op);
            }
        }
        return clause.toString();
    }
}
