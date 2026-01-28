rootProject.name = "HelloSpringExperiments"
include("src:test:resources")
findProject(":src:test:resources")?.name = "resources"
