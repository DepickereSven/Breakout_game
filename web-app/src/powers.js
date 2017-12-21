
const powerTypes = [
  'MORE_POINTS',
  'LESS_POINTS',
  'LARGER_BALL',
  'SMALLER_BALL',
  'LARGER_PALET',
  'SMALLER_PALET',
  'FASTER',
  'SLOWER'
]
exports.powerTypes = powerTypes

exports.getPowerImg = (powerId = 1) => {
  const type = powerTypes[powerId - 1]
  return `images/powers/${type}.png`
}
