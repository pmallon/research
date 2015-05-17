# research
   * Trader Application
         This accepts a JSON request on {HOST}:8080/submitTrade
         It uses spring boot framework so can be started as a simple java application or reconfigure the build to generate a war file 
         for target web/application server.
         
         After business validation . It hands the request off to a Task executor to handle the process on the trade , which is to 
         stage the data in a hsqldb ( can be easily reconfigured to talk to other DB systems)
         
         The report screen is just a simple summary by Country.{HOST}:8080/report
         
         There is included som POSTMAN JSON examples.
