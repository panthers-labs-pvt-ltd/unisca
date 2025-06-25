package org.panthers.labs.chimera.keyclock;

import org.panthers.labs.chimera.keyclock.config.KeycloakSetupProperties;
import org.keycloak.admin.client.Keycloak;
import org.panthers.labs.chimera.keyclock.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

public class KeycloakInitializerRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(KeycloakInitializerRunner.class);

    private final Keycloak keycloak;
    private final KeycloakSetupProperties setupProperties;
    private final RealmSetupService realmSetupService;
    private final ClientSetupService clientSetupService;
    private final RoleSetupService roleSetupService;
    private final GroupSetupService groupSetupService;
    private final UserSetupService userSetupService;

    public KeycloakInitializerRunner(Keycloak keycloak,
                                     KeycloakSetupProperties setupProperties,
                                     RealmSetupService realmSetupService,
                                     ClientSetupService clientSetupService,
                                     RoleSetupService roleSetupService,
                                     GroupSetupService groupSetupService,
                                     UserSetupService userSetupService) {
        this.keycloak = keycloak;
        this.setupProperties = setupProperties;
        this.realmSetupService = realmSetupService;
        this.clientSetupService = clientSetupService;
        this.roleSetupService = roleSetupService;
        this.groupSetupService = groupSetupService;
        this.userSetupService = userSetupService;
    }

  @Override
  public void run(String... args) {
    log.info("Starting Keycloak initialization...");
    if (setupProperties.getSetup().isEnabled()) {

      String realmName = setupProperties.getSetup().getRealm();
      String clientId = setupProperties.getSetup().getClient().getClientId();
      realmSetupService.createRealm(realmName);
      clientSetupService.createClient(realmName, setupProperties.getSetup().getClient());
      roleSetupService.createRoles(realmName, clientId, setupProperties.getSetup().getRoles());
      groupSetupService.createGroups(realmName, setupProperties.getSetup().getGroups());
      userSetupService.createUsers(realmName, clientId, setupProperties.getSetup().getUsers());

      log.info("Keycloak initialization completed successfully");
    }
  }
}
