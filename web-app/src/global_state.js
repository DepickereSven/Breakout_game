let state = {}

function set (key, val) {
  state[key] = val
  persist()
}
exports.set = set

const get = (key) => state[key]
exports.get = get

function remove (key) {
  state[key] = undefined
}
exports.remove = remove

function persist () {
  localStorage.setItem('state', JSON.stringify(state))
}
exports.persist = persist

function rehydrate () {
  state = JSON.parse(localStorage.getItem('state')) || {}
}
exports.rehydrate = rehydrate
