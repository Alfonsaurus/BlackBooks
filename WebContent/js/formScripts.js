/**
 * Scripts attached to forms (init.jsp)
 */
var check = function() {
  if (document.getElementById('newPass').value ==
    document.getElementById('newRepeatPass').value) {
	document.getElementById('validator').style.visibility = "visible";
    document.getElementById('validator').style.color = 'green';
  } else {
	document.getElementById('validator').style.visibility = "hidden"; 
  }
}
