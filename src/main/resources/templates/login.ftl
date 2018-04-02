<html>
       <head>
              <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css" rel="stylesheet" id="bootstrap-css">
              <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.js"></script>
              <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"
                           integrity="sha256-T0Vest3yCU7pafRw9r+settMBX6JkKN06dqBnpQ8d30="
                            crossorigin="anonymous">
              </script>
              <style>
                     .row{
                           margin-top: 2%;
                     }
                     .text-head{
                           font-family: Georgia, serif;
                           margin-top:-2%;
                     }
                     
              </style>
       </head>
       
       <body>
              <div class="container">
                     <div class="row" id="pwd-container">
                           <div class="col-md-4"></div>
                           <div class="col-md-4 jumbotron">
                                  <h1 class="text-head">Login</h1> 
                                  <hr/>
                           <section class="login-form">
                                      <form role="form" action="login" method="post">
                                          <div class="form-group">
                                              <label for="username">Username:</label>
                                              <input type="text" class="form-control input-lg" id="username" name="username"/>
                                          </div>
                                          <div class="form-group">
                                              <label for="password">Password:</label>
                                              <input type="password" class="form-control input-lg" id="password" name="password"/>
                                          </div>
                                          <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                          <button type="submit" class="btn btn-primary btn-lg text-center">Submit</button>
                                      </form>
                               </section>  
                     </div> 
                     <div class="col-md-4"></div>   
                     </div>
              </div>
       </body>
</html>
