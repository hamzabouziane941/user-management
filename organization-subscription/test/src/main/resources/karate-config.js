function loadKarateConfig() {

  const KarateContextClass = Java.type('com.hb.test.karate.KarateContext');
  const karateContext = KarateContextClass.getInstance();
  var config = {
     organizationSubscription: karateContext.url("organization-subscription"),
  }
  return config;
}
