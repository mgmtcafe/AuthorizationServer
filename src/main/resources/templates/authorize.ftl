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
                     #mid-text{
                           font-size: 18px;           
                     }      
              </style>     
       </head>
       
       <body>
              <div class="container">
                     <div class="row" id="pwd-container">
                           <div class="col-md-3"></div>
                           <div class="col-md-6 jumbotron">
                               <h1 class="text-head">Please Confirm</h1>
                                  
                               <p id="mid-text">
                                   Do you authorize "${authorizationRequest.clientId}" at "${authorizationRequest.redirectUri}" to access your
                                   protected resources
                                   with scope ${authorizationRequest.scope?join(", ")}?
                               </p>
                               <ul class="list-inline">
                                     <li>
                                             <form id="confirmationForm" name="confirmationForm"
                                                   action="../oauth/authorize" method="post">
                                                 <input name="user_oauth_approval" value="true" type="hidden"/>
                                                 <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                 <button class="btn btn-lg btn-success" type="submit">Approve</button>
                                             </form>
                                     </li>
                                     <li>
                                             <form id="denyForm" name="confirmationForm"
                                                   action="../oauth/authorize" method="post">
                                                 <input name="user_oauth_approval" value="false" type="hidden"/>
                                                 <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                 <button class="btn btn-lg btn-danger" type="submit">Deny</button>
                                         </form>
                                  </li>
                            </ul>
                         </div>
                  <div class="col-md-3"></div>
                  </div>
              </div>
       </body>
</html>

