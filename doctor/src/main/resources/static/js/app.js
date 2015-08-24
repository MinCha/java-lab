// prepare...
var messageQueueBox = {};
var onLoadMessage = {};
var localChanList = {};
$.ajax({
    url: "/messages?userId=1&channelId=1"
})
    .done(function( data ) {
        console.log("received data", data);
        // TODO: 서버로부터 받은 JSON 데이터와 타입이랑 값을 맞출것!
        // mocking...
        onLoadMessage = {
            userId: "javajigi",
            messages: [
                {
                    userId: "doortts",
                    message: "안녕하세요? ㅎㅎ",
                    time: new Date()
                },
                {
                    userId: "vayne",
                    message: "날이 덥네요",
                    time: new Date()
                }
            ],
            channelList: [
                {"#일반":{id: "chan-1"}}
            ]
        };

        _.forEach(onLoadMessage.messages, function(m) {
            addMessageToView(m.userId, m.message);
        });

        addToChannelList("#일반");
    });
//...

function addMessageToView(userId, typedMessage){
    var idBox = "<div class='user-id col-xs-1'>" + userId + " </div>";
    var messageBox = "<div col-xs-11><strong>"  + typedMessage + "</strong></div>"
    $("#contents").append("<div>" + idBox + messageBox + "</div>");
    console.log(messageQueueBox);
}

function submit(){
    //
    // chat message mock actions
    //
    var typedMessage =$('#message').val();

    // add to Message queue
    var uniqueId = Date.now() +"-"+ onLoadMessage.userId;
    messageQueueBox[uniqueId] = typedMessage;
    addMessageToView(onLoadMessage.userId, typedMessage);
    // mock actions end here

    if( isChannelAddMessage(typedMessage) ){
        addToChannelList(typedMessage);
        addMessageToView("", "<span class='info'>새로운 채널<strong> " + typedMessage + " </strong>이/가 추가되었습니다.</span>");
    }

    return false;
}

function clearInput(){
    $('#message').val("");
}

function isChannelAddMessage(message){
    if(message.trim().charAt(0) === "#"){
        return true;
    } else {
        return false;
    }
}

function addToChannelList(name){
    if(!localChanList[name]){
        localChanList.name = {};
        $(".chan-list-item").append("<li>" + name + "</li>");
    }
    // TODO: 서버로 부터 받은 채널 목록과 동기화 시키는 방법 고민할 것
}

document.chatView.onsubmit = function(){
    submit();
    clearInput();
    return false;
}
