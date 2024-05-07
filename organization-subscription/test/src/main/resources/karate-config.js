function loadKarateConfig() {

  const KarateContextClass = Java.type('com.hb.test.karate.KarateContext');
  const karateContext = KarateContextClass.getInstance();
  const url = karateContext.getUrl();
  karate.log('karate context urls : ', url);
  var config = {
     organizationSubscription: karateContext.url.organization-subscription,
  }
  return config;
}
