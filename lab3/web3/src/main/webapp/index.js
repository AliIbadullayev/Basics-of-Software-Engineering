let errorMsg = document.getElementById('error');
let x = null;
let y = null;
let radius = null;
let radiusSelect = document.getElementById('formID:radius');
let xCheckboxes = document.querySelectorAll("input[type=\"checkbox\"]");
let yText = document.querySelector("input[type=\"text\"]");
let canvasPoints = document.getElementById("canvas_points");
let t1 = 0; // variables for check that pressed on checkbox 2 times
let point;
const canone = 68;

// first param take value of checkbox, second - type (radius, x_coordinate)
function storeVar(value){
	makeCheckBox(value);
}

function makeCheckBox(number){

		for (let checkbox of xCheckboxes){
			if (checkbox.title === number) {
				checkbox.setAttribute("enabled", "true");
				x = checkbox.value;
				t1++;
			}
			else checkbox.setAttribute("disabled", "true");
		}
		//if click on checkbox second time than all buttons must be enabled
		if (t1 === 2){
			for (let checkboxes of xCheckboxes){
				checkboxes.removeAttribute("disabled");
			}
			x = null;
			t1=0;
		}
}

function drawPoint(x, y, r, result){
	let ctxPoints = canvasPoints.getContext('2d');
	ctxPoints.fillStyle = result === 'bad' ? 'red' : 'green';
	ctxPoints.beginPath();
	ctxPoints.arc(
		x / r * canone + 220 / 2,
		- y / r * canone + 220 / 2,
		2, 0, 2 * Math.PI);
	ctxPoints.fill();
}

function loadTablePoints() {
	let table = document.getElementById("result-table");
	for (i=1; i<table.rows.length; i++)
	{
		someX = table.rows[i].cells[0].childNodes[0].data;
		someY = table.rows[i].cells[1].childNodes[0].data;
		someR = table.rows[i].cells[2].childNodes[0].data;
		res = table.rows[i].cells[3].childNodes[0].data;
		drawPoint(someX, someY, someR, res);

	}
}

function getRadius(){
	radius = radiusSelect.options[radiusSelect.selectedIndex].value;
}

function validateForm(){
	y = yText.value;

	if ( x === null || x===""){

		errorMsg.innerHTML="Некорректное значение 'X'!";
		return false;
	} else if (isNaN(y) || y === "" ||  y >= 5 || y <= -5){
		errorMsg.innerHTML="Некорректное значение 'Y'!";
		return false;
	} else if ( radius === null || radius===""){

		errorMsg.innerHTML="Некорректное значение 'R'!";
		return false;
	} else {
		return true;
	}
}

	let graph = document.querySelector(".graph");
	// It's important to add an load event listener to the object,
	// as it will load the svg doc asynchronously
	graph.addEventListener("load",function(){


		// get the inner DOM of alpha.svg
		let svgDoc = graph.contentDocument;
		// get the inner element by id
		let rect = svgDoc.getElementById("svg-graph");

		let rx = svgDoc.getElementById("rx");
		let minus_rx = svgDoc.getElementById("minus_rx");
		let minus_halfrx = svgDoc.getElementById("minus_halfrx");
		let halfrx = svgDoc.getElementById("halfrx");

		let ry = svgDoc.getElementById("ry");
		let minus_ry = svgDoc.getElementById("minus_ry");
		let minus_halfry = svgDoc.getElementById("minus_halfry");
		let halfry = svgDoc.getElementById("halfry");
		point = svgDoc.getElementById("point")

		rect.addEventListener("mouseenter",function other(){
			if (radius === null){
				errorMsg.innerHTML = "Пожалуйста выберите радиус!";
				rx.innerHTML = 'R';
				minus_rx.innerHTML = "-R";
				halfrx.innerHTML = 'R/2' ;
				minus_halfrx.innerHTML = "-R/2" ;

				ry.innerHTML = 'R';
				minus_ry.innerHTML = "-R";
				halfry.innerHTML = 'R/2' ;
				minus_halfry.innerHTML = "-R/2" ;
			} else {

				rx.innerHTML = radius;
				minus_rx.innerHTML = "-" + radius;
				halfrx.innerHTML = radius / 2 ;
				minus_halfrx.innerHTML = "-" + radius/2 ;

				ry.innerHTML = radius;
				minus_ry.innerHTML = "-" + radius;
				halfry.innerHTML = radius / 2;
				minus_halfry.innerHTML = "-" + radius/2 ;
				errorMsg.innerHTML = null;
			}

		}, false);

		// add behaviour
		rect.addEventListener("click",function clicked(evt){
			if (radius === null){
			errorMsg.innerHTML = "Пожалуйста выберите радиус!";
			} else {

				let xcheck = ((evt.clientX-110)/33*radius/2).toFixed(0);
				let ycheck = (-(evt.clientY-110)/33*radius/2).toFixed(3);
				validateSvgX(xcheck);
				validateSvgY(ycheck);
				if (validateSvg(x, y)){

					errorMsg.innerHTML = null;
					for (let checkbox of xCheckboxes){
						// don't do === instead ==
						if (x == checkbox.title) {
							checkbox.removeAttribute("disabled");
							checkbox.checked = true;
						}
						else {
							checkbox.checked = false;
							checkbox.setAttribute("disabled", "true");
						}
					}

					yText.value = y;
					// document.getElementById('timezone').value = new Date().getTimezoneOffset();
					document.getElementById('formID:submit').click();
				}else {
					validateForm();
					return false;
				}
			}
		}, false);
	}, false);

	function validateSvgX(xcheck){
		if (xcheck > 1 || xcheck < -5){
			x = null;
		}  else {
			x = xcheck;
		}
	}
	function validateSvgY(ycheck){
		if (ycheck > 3 || ycheck < -3){
			y = null;
		}  else {
			y = ycheck;
		}
	}
	function validateSvg(x, y){
		return !(x == null || y == null);
	}

	// when window load we draw on svg a point with old params
	window.onload = function (){
		for (let checkboxes of xCheckboxes){
			checkboxes.removeAttribute("disabled");
			checkboxes.removeAttribute("checked")
		}
		yText.setAttribute("value","");
		loadTablePoints();
	}
