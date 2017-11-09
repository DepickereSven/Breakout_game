if (navigator.userAgent.indexOf('Smash_It') > 1) {
  setTimeout(function () {
    $('.container').css('height', window.innerHeight)
    $('#start').addClass('load')
  }, 4500)
} else {
  $('.container').css('height', window.innerHeight)
  $('#start').addClass('load')
}
// document.querySelector('body').style.height = window.innerHeight + 'px'
