
const state = window.state = {}

exports.set = function (key, val) {
  state[key] = val
}

exports.get = function (key) {
  return state[key]
}
