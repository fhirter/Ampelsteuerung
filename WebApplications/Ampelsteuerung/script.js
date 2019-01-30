
'use strict'

document.querySelector('#projektID').addEventListener("click", updateProjectIDDatalist);
document.querySelector('#projektID').addEventListener("change", listProjectWithSelectedProjectnameOnTableCrossroadSettings);
document.querySelector('#projektltr').addEventListener("click", updateProjectLtrDatalist);
document.querySelector('#projektltr').addEventListener("change", listProjectsWithSelectedProjectLtrOnTableCrossroadSettings);
document.querySelector('#projektstart').addEventListener("click", listProjectsWithSelectedDataOnTableCrossroadSettings);
document.querySelector('#projektende').addEventListener("click", listProjectsWithSelectedDataOnTableCrossroadSettings);


listAllProjectsOnTableCrossroadSettings();

/*
* updateProjectIDDatalist()
*
* Aktualisiert nach dem "click"-Event anhand dem Array projects die Datalist der Projekte.
*
* Schweizer Patrick
*
*/
  function updateProjectIDDatalist() 
  {
	var options = '';
	
	listAllProjectsOnTableCrossroadSettings();
	
	for(var i = 0; i < projects.length; i++)
	{
		if(options.indexOf(projects[i].name) == -1)
		{	
			options += '<option value="'+projects[i].name+'" />';
		}
	}
	
	document.getElementById('projektIDList').innerHTML = options;
  }
  
  
/*
* updateProjectLtrDatalist()
*
* Aktualisiert nach dem "click"-Event anhand dem Array projects die Datalist der Projektleiter.
*
* Schweizer Patrick
*
*/  
  function updateProjectLtrDatalist() 
  {
	var options = '';
	
	listAllProjectsOnTableCrossroadSettings();
	
	for(var i = 0; i < projects.length; i++)
	{
		if(options.indexOf(projects[i].persons[0].name) == -1)
		{	
			options += '<option value="'+projects[i].persons[0].name+'" />';
		}
	}
	document.getElementById('projektLeiterName').innerHTML = options;
  }
  
  
/*
* listProjectWithSelectedProjectnameOnTableCrossroadSettings()
*
* Aktualisiert nach dem "change"-Event die Tabelle der Prjektausgabe gemaess dem angewaehlten Projekt
*
* Schweizer Patrick
*
*/ 
  function listProjectWithSelectedProjectnameOnTableCrossroadSettings()
  {	
    var projectIDname = '';
	var projectIDvalue = 0;
	
	projectIDname = document.getElementById("projektID").value;
	
	if(projectIDname != "")
	{		
		for(var i = 0; i < projects.length; i++)
		{
			if(projects[i].name == projectIDname)
			{
				projectIDvalue = i;
			}
		}
		
		refreshTableCrossroadSettingsWithOneProject(projectIDvalue);
	}else
	{
		listAllProjectsOnTableCrossroadSettings();
	}
  }
  
  
/*
* listProjectsWithSelectedProjectLtrOnTableCrossroadSettings()
*
* Aktualisiert nach dem "change"-Event die Tabelle der Prjektausgabe gemaess dem angewaehlten Projektleiter
*
* Schweizer Patrick
*
*/ 
  function listProjectsWithSelectedProjectLtrOnTableCrossroadSettings()
  {	
  	var optionsArray = [];
	var optionArrayCounter = 0;
    var projectLtrName = '';
	
	projectLtrName = document.getElementById("projektltr").value;
	
	if(projectLtrName != "")
	{		
		for(var i = 0; i < projects.length; i++)
		{
			if(projectLtrName == projects[i].persons[0].name)
			{
				optionsArray[optionArrayCounter] = i;
				optionArrayCounter ++;				
			}
		}

		refreshTableCrossroadSettingsWithListOfProjects(optionsArray);
	}else
	{
		listAllProjectsOnTableCrossroadSettings();			
	}
  }
  
  
/*
* listProjectsWithSelectedDataOnTableCrossroadSettings()
*
* Funktion ist aktuell noch nicht implementiert!!!!!!!!!!
* Gibt nach dem "change"-Event vom Start- oder Enddatum eine Meldung aus.
*
* Schweizer Patrick
*
*/
  function listProjectsWithSelectedDataOnTableCrossroadSettings()
  {
	  
	alert("Projektfilterung nach Datum ist akutell noch nicht umgesetzt.");
  }  
  
  
/*
* listAllProjectsOnTableCrossroadSettings()
*
* Gibt in der Tabelle Projektausgabe saemtlichen Projekte welche im Array projects eingetragen sind aus
*
* Schweizer Patrick
*
*/   
  function listAllProjectsOnTableCrossroadSettings()
  {	

	var options = '';
	
	for(var i = 0; i < projects.length; i++)
	{
		if(i < (projects.length - 1))
		{
			options = refreshTableCrossroadSettings(i, options, false);
		}else
		{
			refreshTableCrossroadSettings(i, options, true);
		}
	}
  }
   
  
/*
* refreshTableCrossroadSettingsWithOneProject()
*
* Gibt in der Tabelle Projektausgabe NUR ein gemaess projektIDvalue angewaehlte Projekt aus. (1 Projekt!!)
* 
* Aktualisiert das Kreuzungsbild gemaess projektIDvalue
*
* Schweizer Patrick
*
*/    
  function refreshTableCrossroadSettingsWithOneProject(projectIDvalue)
  {
  
	refreshTableCrossroadSettings(projectIDvalue, '', true);

	document.getElementById('pictureKreuzung').src = "img/" + projects[projectIDvalue].picture;	  	  
  }
  
  
/*
* refreshTableCrossroadSettingsWithListOfProjects(optionsArray)
*
* Gibt in der Tabelle Projektausgabe mehrere Projekte gemaess optionsArray aus. 
* Wird benoetigt, wein bsp. ein Projektleiter mehrere Projekte bearbeitete
*
* Schweizer Patrick
*
*/   
  function refreshTableCrossroadSettingsWithListOfProjects(optionsArray)
  {
	var options = '';
	
	for(var i=0; i < optionsArray.length; i++)
	{
		if(i < (optionsArray.length - 1))
		{
			options = refreshTableCrossroadSettings(optionsArray[i], options, false); 
		}else
		{
			refreshTableCrossroadSettings(optionsArray[i], options, true); 
		}
	}		
  }
  
   
/*
* refreshTableCrossroadSettings(projectIDvalue, preValues, writeOutput)
* 
* Erstellt den HTML Code fuer die Darstellung der Tabelle Projektausgabe
*
* Gibt Defaultbild der Kreuzung aus
*
* projectIDvalue = ProjektID --> projects[projectIDvalue]
* preValues = Werden mehrere Projekte ausgegeben, sind die Vorgaengerprojekte in diesem "String" abgelegt. 
*						--> Wert von preValues wird mit dem neuen Projekt ergaent und als options zurueckgegeben
* writeOptput = true 	--> Projeke werden als HTML Code ausgegeben / 
				false 	--> Es wurde noch nicht alle Projekte in preValues abgelegt. HTML Tabelle soll noch nicht aktualisiert werden
*
* Schweizer Patrick
*
*/  
  function refreshTableCrossroadSettings(projectIDvalue, preValues, writeOutput)
  {
	var options = preValues;	  

	options += '<tr><th>Projektname:</th>';
	options += '<th>'+ projects[projectIDvalue].name +'</th> </tr>';
	
	options += '<tr><td>Strassennamen:</td>';
	options += '<td>'+ projects[projectIDvalue].streets +'</td> </tr>';
	
	options += '<tr><td>Projektleiter:</td>';
	options += '<td>'+ projects[projectIDvalue].persons[0].name +'</td> </tr>';
	
	options += '<tr><td>'+ projects[projectIDvalue].persons[1].role +':</td>';
	options += '<td>'+ projects[projectIDvalue].persons[1].name +'</td> </tr>';
	
	options += '<tr><td>'+ projects[projectIDvalue].persons[2].role +':</td>';
	options += '<td>'+ projects[projectIDvalue].persons[2].name +'</td> </tr>';
	
	options += '<tr><td>Projektstart:</td>';
	options += '<td>'+ projects[projectIDvalue].start +'</td> </tr>';
	
	options += '<tr><td>Projektende:</td>';
	options += '<td>'+ projects[projectIDvalue].end +'</td> </tr>';

	if(writeOutput == true)
	{
		document.getElementById('projektausgabe').innerHTML = options;	
		document.getElementById('pictureKreuzung').src = "img/EinstiegsbildKreuzung.jpg";	  	  		
	}else
	{
		return options;
	}
  }
  
  