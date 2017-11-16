const path = 'stats.html'
exports.path = path

exports.view = class StatsView {
  constructor (viewManager) {
    this.path = path
    this.hideHeader = false
    this.viewManager = viewManager
  }
  onLoad () {
    const nationsCode = [{
      player_won: {
        name: 'Jonas',
        countryCode: 'BE'
      },
      player_lost: {
        name: 'Fritz',
        countryCode: 'DE'
      },
      points: 250,
      time: 120
    }, {
      player_won: {
        name: 'Thomas',
        countryCode: 'CH'
      },
      player_lost: {
        name: 'Neymar',
        countryCode: 'PT'
      },
      points: 500,
      time: 50
    }, {
      player_won: {
        name: 'Ishan',
        countryCode: 'CN'
      },
      player_lost: {
        name: 'Max',
        countryCode: 'NL'
      },
      points: 600,
      time: 30
    }, {
      player_won: {
        name: 'Sven',
        countryCode: 'EU'
      },
      player_lost: {
        name: 'LX',
        countryCode: 'SG'
      },
      points: 600,
      time: 30
    }]
    function createTableStats () {
      let $resultaatString = '<table class="table"><thead><tr><th></th><th>Players</th><th>Points</th><th>Time</th></tr></thead>'
      $resultaatString += '<tbody>'

      $.each(nationsCode, function (index, item) {
        const wonPlayerCC = (item.player_won.countryCode).toLowerCase()
        const lostPlayerCC = (item.player_lost.countryCode).toLocaleLowerCase()
        $resultaatString += createRow(wonPlayerCC, item, item.points)
        $resultaatString += createRow(lostPlayerCC, item, 0)
      })
      $resultaatString += '</tbody></table>'
      return $resultaatString
    }

    function createRow (playerCC, item, points) {
      let tr = '<tr>'
      let ctd = '</td>'
      let ctr = '</td></tr>'
      let $resultaatString = ''
      $resultaatString += tr + '<td class="country">'
      $resultaatString += createImgString(playerCC)
      $resultaatString += ctd + '<td class="name">'
      if (points === 0) {
        $resultaatString += item.player_lost.name
      } else {
        $resultaatString += item.player_won.name
      }
      $resultaatString += ctd + '<td class="points">'
      $resultaatString += points
      if (points === 0) {} else {
        $resultaatString += ctd + '<td class="time">'
        $resultaatString += calcTime(item.time)
      }
      $resultaatString += ctr
      return $resultaatString
    }

    function timePadding (time, isBack) {
      const newtime = time.toString()
      if (newtime.length < 2) {
        return isBack ? newtime + '0' : '0' + newtime
      } else {
        return newtime
      }
    }

    function calcTime (timeInSec) {
      const min = Math.floor(timeInSec / 60)
      const sec = timeInSec - (min * 60)
      return timePadding(min) + ':' + timePadding(sec)
    }

    $('.stats_multiplayer').html(createTableStats())

    function createImgString (nation) {
      return '<img src="images/nations/' + nation + '.png" alt="' + nation + '" title="' + nation + '"/>'
    }

  }

  onUnload () {}
}
