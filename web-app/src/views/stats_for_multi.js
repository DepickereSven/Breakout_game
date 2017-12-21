const path = 'stats.html'
const ScoresRequestAction = require('../actions/scores_request')
exports.path = path

exports.view = class StatsView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = false
    this.viewManager = viewManager
    this.scores = null
  }
  
  createTableStats () {
    let rows = '';
    $.each(this.scores, (index, item) => {
      const wonPlayer = item.user_won
      rows += this.createRow(wonPlayer, item.points, item.time)

      const lostPlayer = item.user_lost
      rows += this.createRow(lostPlayer, '', '')
    })

    let res = `<table class="table">
                    <thead><tr><th></th><th>Players</th><th>Points</th><th>Time</th></tr></thead>
                    <tbody>${rows}</tbody>
               </table>`
    return res
  }

  createRow (player, points, time) {
    return `<tr>
                <td class="country"> ${this.createImgString(player.country.toLowerCase())}</td>
                <td class="name"> ${player.name}</td>
                <td class="points"> ${points}</td>
                <td class="time"> ${time === '' ? '': this.calcTime(time)}</td>
            </tr>`;
  }

  timePadding (time, isBack) {
    const newtime = time.toString()
    if (newtime.length < 2) {
      return isBack ? newtime + '0' : '0' + newtime
    } else {
      return newtime
    }
  }

  calcTime (timeInSec) {
    const min = Math.floor(timeInSec / 60)
    const sec = timeInSec - (min * 60)
    return this.timePadding(min) + ':' + this.timePadding(sec)
  }

  createImgString (country) {
    return '<img src="images/nations/' + country + '.png" alt="' + country + '" title="' + country + '"/>'
  }

  setScores(scores){
    this.scores = scores.sort((a,b) => {
      if (a.points === b.points){
        return a.time - b.time;
      }else{
        return b.points - a.points;
      }
    })
    $('.stats_multiplayer').html(this.createTableStats())
  }

  onLoad () {
    window.wsClient.send(ScoresRequestAction.create())
  }

  onUnload () {}
}
