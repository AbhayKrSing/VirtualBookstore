const app=angular.module("playWithAngularJS",[]);




app.controller("Mycontroller", function($rootScope,$scope,$http){
     $scope.formData={
		username:"",
		email:"",
		password:""
	}
	$http.get("http://localhost:8080/api/user").then((res)=>{
		$scope.Users=res.data;
	}).catch((err)=>{
		console.log(err.message);
	})
	
	$scope.addData=function(){
	$http.post("http://localhost:8080/api/user",$scope.formData)
	.then((res)=>{
		$scope.Users.push(res.data);
	}).catch((err)=>{
		console.log("Error occured :"+ err.message);
	})		
	}
	
	$scope.editUser=function(user){
	   $scope.editingUser=true;
	   $scope.currentEditUser=angular.copy(user);
	}
	
	$scope.updateUser=function(){
		$scope.UserBody={};
		$scope.UserBody.username=$scope.currentEditUser.username
		$scope.UserBody.email=$scope.currentEditUser.email
		$scope.UserBody.password=$scope.currentEditUser.password
	   $http.put("http://localhost:8080/api/user/"+$scope.currentEditUser.id,$scope.UserBody).then((res)=>{
		  $scope.Users=$scope.Users.map((ele)=>{
			 return ele.id==res.data.id?res.data:ele;
		  })
	   }).catch((err)=>{
		  console.log("Error occured" + err.message); 
	   })
	}
	
	
	$scope.deleteUser=function(user){
		$http.delete("http://localhost:8080/api/user/"+user.id).then((res)=>{
			$scope.Users=$scope.Users.filter((ele)=>{
				return ele.id != user.id
			})
		})
	}
})
