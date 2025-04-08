const app=angular.module("playWithAngularJS",["ngRoute"]);


app.config(function($routeProvider) {
  $routeProvider
  .when("/users", {
    template : `<div class="grid-container" >
	 <user-card ng-repeat="user in Users" user="user" on-delete="deleteUser(user)" on-edit="editUser(user)">
	 </user-card>
	</div>`
  })
   .when("/adduser",{
	templateUrl:"./templates/add-user.html"
   })
   .when('/edituser/:userId', {
      templateUrl: './templates/edit-user.html',
    })
    .otherwise({
      redirectTo: '/users'
    });
});


app.directive("userCard",[function(){
	return {
		restrict:'E',
		scope:{
			user:'=',
			onDelete:'&',
			onEdit:'&'
		},
		replace:true,
		templateUrl:'templates/user-card.html'
	}
}])

app.directive("userForm",[function(){
	return{
    restrict:'E',		
	scope:{
		formData:'=',
		onSubmit:'&'
	},
	replace:true,
	templateUrl:"templates/user-form.html"
	}
}])




app.controller("Mycontroller", function($rootScope,$scope,$http,$location){
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
		$location.path('/users');
	}).catch((err)=>{
		console.log("Error occured :"+ err.message);
	})		
	}
	
	$scope.editUser=function(user){
	   $scope.editingUser=true;
	   $scope.currentEditUser=angular.copy(user);
	   $location.path('/edituser/' + user.id);
	   
	}
	
	$scope.updateUser=function(){
		$scope.UserBody={};
		$scope.UserBody.username=$scope.currentEditUser.username
		$scope.UserBody.email=$scope.currentEditUser.email
		$scope.UserBody.password=$scope.currentEditUser.password
	   $http.put("http://localhost:8080/api/user/"+$scope.currentEditUser.id,$scope.UserBody).then((res)=>{
		$location.path('/users');
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
