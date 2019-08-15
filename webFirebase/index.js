
var question=document.getElementById('question');
var false1 =document.getElementById('false1');
var false2 =document.getElementById('false2');
var false3 =document.getElementById('false3');
var true4 =document.getElementById('true4');
var quoteContent =document.getElementById('quoteContent');


function setVisibleView(view, state){
	var viewIndex = document.querySelectorAll (view);
	for (var i = viewIndex.length - 1; i >= 0; i--) {
		viewIndex[i].style.display = state;
	}
}

function setVisibleQuestion(){
	setVisibleView(".index", "none");
	setVisibleView(".question", "initial");
	setVisibleView(".quote", "none");
}

function setVisibleQuote(){
	setVisibleView(".index", "none");
	setVisibleView(".question", "none");
	setVisibleView(".quote", "initial");
}

function setVisibleIndex(){
	setVisibleView(".index", "initial");
	setVisibleView(".question", "none");
	setVisibleView(".quote", "none");
}

function back(){
	setVisibleIndex();
}



function createQuestion() {
	var firebaseRef = firebase.database().ref().child("MultipleChoiceQuestion");
	var f1 = false1.value;
	var f2 = false2.value;
	var f3 = false3.value;
	var t = true4.value;
	var q = question.value;

	if(f1=="" ||f2=="" ||f3=="" ||t=="" ||q==""){
		window.alert("Hãy nhập đủ thông tin");
	}
	else{
		var keyFirebase = firebaseRef.push().getKey();
		firebaseRef.child(keyFirebase).child("false1").set(f1);
		firebaseRef.child(keyFirebase).child("false2").set(f2);
		firebaseRef.child(keyFirebase).child("false3").set(f3);
		firebaseRef.child(keyFirebase).child("true").set(t);
		firebaseRef.child(keyFirebase).child("question").set(q);
		cancel();
	}
}

function cancel() {
	false1.value = "";
	false2.value = "";
	false3.value = "";
	true4.value = "";
	question.value = "";
	quoteContent.value = "";
}

function createQuote(){
	var firebaseRef = firebase.database().ref().child("Quotes");
	var content = quoteContent.value;

	if(content == ""){
		window.alert("Hãy nhập nội dung câu nói");
	}
	else{
		var keyFirebase = firebaseRef.push().getKey();
		firebaseRef.child(keyFirebase).set(content);
		cancel();
	}
}