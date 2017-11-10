$('.container_singleplayer_gamemode a img').on('click', function () {
  generate()
})

function generate () {
  const $selector = $(this)
  let $resultaatstring = ''
  let min = $selector.attr('data-min')
  let max = parseInt($selector.attr('data-max')) + 1
  for (max; min < max; min++) {
    $resultaatstring += '<a href="#" class="level" data-level="' + min + '"><span class="levelName">' + min + '</span></a>'
  }
  $('.generate_levels_singeplayer').html($resultaatstring)
}
