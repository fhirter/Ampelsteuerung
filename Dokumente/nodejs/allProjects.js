exports.projectNames = function () {
	
	var allProjects = [];
	
	for (var i = 0; i < projects.length; i++) {
	allProjects.push(projects[i].name);	
	}
	
  return allProjects;
};