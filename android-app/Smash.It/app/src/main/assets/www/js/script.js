/**
 * Created by svend on 4/04/2017.
 */

/*********************************************PLAY_MUSIC********************************************/

var select  = function(){
    var val = $(this).attr('data-val');
    playMusic(val);
};

var playMusic = function(audioName){
    var snd = new Audio('music/' + audioName + '.mp3');
    snd.play();
};

/*********************************************INIT********************************************/
var init = function () {
    $('.click_me').on('click', select);
};
$(document).ready(init());