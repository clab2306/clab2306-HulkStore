pipeline{
 
		agent {
			label 'Slave1'
		}
    
		triggers {
			pollSCM('@hourly')
		}

		options {
			buildDiscarder(logRotator(numToKeepStr:'5'))
			disableConcurrentBuilds()
		}
		
		tools{
			jdk 'JDK8_Centos'
			gradle 'Gradle6.1_Centos' 
		}

		stages {
			
			stage('Checkout Git'){
				steps{
				   checkout([$class: 'GitSCM', branches: [[name: '${BRANCH_NAME}']], doGenerateSubmoduleConfigurations: false, extensions: [], gitTool: 'Git_Centos', submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'clab2306', url: 'https://github.com/clab2306/clab2306-HulkStore]])
				}
			}
			 
			stage('Build Frontend'){
				parallel{
					stage('DEV Environment') { 
			   			when{
							branch 'develop'
						}
			   			steps { 
							dir('store-web'){
								sh 'npm install && ng build --prod --configuration=dev ---base-href /admin-web/'
							}		
						}
					}

					stage('Default Environment') { 
						when{
							not{
								branch 'develop'
							}
						}
			   			steps { 
							dir('store-web'){
								sh 'npm install && ng build --prod --base-href /store-web/'
							}
						}
					}
				}
			}

			stage('Unit Test & Coverage Frontend') {
				steps {
    				 dir('store-web'){
    					sh 'ng test --watch=false --code-coverage'
    				 }
    			}
    		}
			
			stage('Clean Backend') {
               steps {
					sh 'gradle --b ./store/build.gradle clean'
               }
            }

			stage('Unit Test & Coverage Backend') {
				steps {
					sh 'gradle --b ./store/build.gradle test'
					sh 'gradle --b ./store/build.gradle jacocoTestReport'
				}
			}          
			
			stage('Static code revision SonarQube') {
				when{
					anyOf{
						branch 'develop';
						branch 'master'
					}
				}
				steps{
					withSonarQubeEnv('Sonar') {
						sh "${tool name: 'SonarScanner', type: 'hudson.plugins.sonar.SonarRunnerInstallation'}/bin/sonar-scanner -Dsonar.projectKey=co.com.todo1: Store
						-Dsonar.projectName=Hulk-Store
						-Dproject.settings=sonar-project.properties"
				}
           }
			}}
			
			stage('Build Backend') { 
			   steps {
					sh 'gradle --b ./store/build.gradle build -x test'
				}
		}
    
		post { 
			failure { 
				mail( to: 'claudiapat.lopez@gmail.com,
					body: "Build failed in Jenkins: Project: ${env.JOB_NAME} Build /n Number: ${env.BUILD_NUMBER} URL de build: ${env.BUILD_NUMBER}/n/n Please go to ${env.BUILD_URL} and verify the build", 
					subject: "ERROR CI: Project name → ${env.JOB_NAME}")
            } 
        }
    }  
	