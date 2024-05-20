function loadKarateConfig() {

  const HttpConfiguration = Java.type('com.hb.test.common.HttpConfiguration');
  karate.log('Api base urls : ', HttpConfiguration.urls());
  return HttpConfiguration.urls();
}
