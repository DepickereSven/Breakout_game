const URL_IP = 'http://www.geoplugin.net/json.gp?jsoncallback=?'
$.getJSON(URL_IP, function (data) {
  const nationsCode = JSON.stringify(data.geoplugin_countryCode, null, 2)
  console.log(nationsCode)
})
