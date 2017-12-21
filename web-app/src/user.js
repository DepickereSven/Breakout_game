const UserLoginRequestAction = require('./actions/user_login_request')
const constants = require('./constants')

exports.User = class User {
  constructor ({ userID, username, imageUrl, email, country, smashbit }) {
    this.userID = userID
    this.username = username
    this.imageUrl = imageUrl
    this.email = email
    this.country = country
    this.smashbit = smashbit
  }

  setSmashbit(smashbit){
    this.smashbit = smashbit;
  }
}

function getCountryCode (callback) {
  $.getJSON('http://ip-api.com/json', function (data) {
    if (data) {
      callback(data.countryCode)
    }
  })
}

function setToken (token) {
  if (constants.IS_ANDROID_APP) {
    return
  }
  if (token) {
    localStorage.setItem('token', token)
  } else {
    localStorage.removeItem('token')
  }
}
exports.setToken = setToken

function getToken () {
  if (constants.IS_ANDROID_APP) {
    return null
  }
  return localStorage.getItem('token')
}
exports.getToken = getToken

function signIn (token) {
  window.viewManager.go('loading.html')
  setToken(token)

  getCountryCode(function (country) {
    window.wsClient.send(UserLoginRequestAction.create({ token, country }))
  })
}
exports.signIn = signIn
window.onAndroidSignIn = signIn

function signOut () {
  setToken(null)
  window.user = undefined
  window.wsClient.reset()

  if (constants.IS_ANDROID_APP) {
    SmashIt.logoutInAndroid()
  } else {
  }

  window.viewManager.go('login.html')
}
exports.signOut = signOut
