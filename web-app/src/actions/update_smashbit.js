/**
 * @module actions/update_smashbit.js
 */

exports.handler = function (action) {
  const smashbit = action.smashbit;
  window.user.setSmashbit(smashbit);
}
