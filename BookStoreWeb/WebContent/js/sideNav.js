/**
 * 
 */

var websocket = null;

function connect() {	
    var wsURI = 'ws://' + window.location.host + '/BookStoreWeb/websocket/helloName';
    websocket = new WebSocket(wsURI);

    websocket.onopen = function() {
        console.log("Open");
    };
    websocket.onmessage = function(event) {
    	console.log(event.data);
    	$("#danmu").danmu("addDanmu", {
    		text:event.data,
    		color:"white",
    		size:1,
    		position:0,
    		time:$('#danmu').data("nowTime") + 1
    	});
    };
    websocket.onerror = function(event) {
        console.log("Error");
    };
    websocket.onclose = function() {
        console.log("Close");
    };
}

function disconnect() {
    if (websocket !== null) {
        websocket.close();
        websocket = null;
    }
    message.setAttribute("class", "message");
    message.value = 'WebSocket closed.';
    // log the event
}

function sendMessage() {
    if (websocket !== null) {
        var content = document.getElementById('danmutext').value;
        websocket.send(content);
        document.getElementById('danmutext').value = "";
    } else {
        console.log("Please refresh!");
    }
}

$(document).ready(function () {
	connect();
	
    $(".button-collapse").sideNav();
    
    $("#danmu").danmu({
		top: 0,
		height: 180,  //弹幕区高度
		width: document.body.scrollWidth,   //弹幕区宽度
		zindex :100,   //弹幕区域z-index属性
		speed:7000,      //滚动弹幕的默认速度，这是数值值得是弹幕滚过每672像素所需要的时间（毫秒）
		sumTime:65535,   //弹幕流的总时间
		danmuLoop:false,   //是否循环播放弹幕
		defaultFontColor:"#FFFFFF",   //弹幕的默认颜色
		fontSizeSmall:14,     //小弹幕的字号大小
		FontSizeBig:20,       //大弹幕的字号大小
		opacity:"0.75",          //默认弹幕透明度
		topBottonDanmuTime:6000,   // 顶部底部弹幕持续时间（毫秒）
		SubtitleProtection:false,     //是否字幕保护
		positionOptimize:false,         //是否位置优化，位置优化是指像AB站那样弹幕主要漂浮于区域上半部分
		
		maxCountInScreen: 40,   //屏幕上的最大的显示弹幕数目,弹幕数量过多时,优先加载最新的。
		maxCountPerSec: 10      //每分秒钟最多的弹幕数目,弹幕数量过多时,优先加载最新的。
	});
	$('#danmu').danmu('danmuStart');
});