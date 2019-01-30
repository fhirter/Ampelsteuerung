
'use strict'

	document.querySelector('#allProjects').addEventListener("click", function(event){
			event.preventDefault();
			startRequest("");
		});
		
	document.querySelector('#projektID').addEventListener("click", function(event){
			event.preventDefault();
			startRequest("?id=0");
//			startRequest("?id=" + document.getElementById(projektID).value);
		});

		
	document.querySelector('#projektstart').addEventListener("change", projectDateStart);
	document.querySelector('#projektstart').addEventListener("change", projectDateEnd);
	
	function projectDateStart()
	{


	}
	
	function projectDateEnd()
	{

	}
	
	function startRequest(url) 
	{

		var request = new XMLHttpRequest();
		var test = "http://192.168.35.46:8080/projects" + url;

//		request.open("GET","https://berghirt.ch/Ampelsteuerung/projects" + url);
		request.open("GET", "http://192.168.35.46:8080/projects" + url);
	
		request.addEventListener('load', function(event) {
			if (request.status >= 200 && request.status < 300) 
			{
				console.log(request.responseText);
				document.getElementById('projectOutput').innerText = request.responseText;
			} else 
			{
				console.warn(request.statusText, request.responseText);
			}
		});
		request.send();
	}
