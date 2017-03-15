import akka.actor.{ ActorSystem, Actor, Props}
import akka.event.Logging
import akka.util.Timeout

import akka.http.scaladsl.Http

import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import com.typesafe.config.{ Config, ConfigFactory}

import scala.concurrent.Future
import scala.util.{Try, Success, Failure} 

object SearchActor {
    case class Search(searchString: String)
    case object Goodbye
}

class SearchActor extends Actor {
    val log = Logging(context.system, this)

    import SearchActor._
    def receive = {
        case Search(searchString)  => log.info(s"Searching by string = $searchString.")
        case _                  => log.info("Someone said goodbye to me")
    }
}

trait RequestTimeout {
    import scala.concurrent.duration._
    def requestTimeout(config: Config): Timeout = {
        val t = config.getString("spray.can.server.request-timeout")
        val d = Duration(t)
        FiniteDuration(d.length, d.unit)
    }
}

object SearchSystem extends App with RequestTimeout {
    
    //val config = ConfigFactory.load()
    //val host = config.getString("http.host")
    //val port = config.getInt("http.port")

    implicit val system = ActorSystem()
    implicit val ec = system.dispatcher

    //val api = new RestApi(system, requestTimeout(config)).routes

    implicit val materializer = ActorMaterializer()
    //val bindingFuture : Future[ServerBinding] = Http().bindAndHandle(api, host, port)

    import SearchActor._
    val myActor = system.actorOf(Props[SearchActor], "mySearchActor2")
    

    myActor ! Search("stringKey")

    def sqr(x: Int) = x * x
    val n22 = 22
    val futureSquare = Future {
        val retVal = sqr(n22)
        retVal
    }  
    futureSquare.onComplete({
        case Success(listInt) => {
          println("Int is " + listInt)
        }
        case Failure(exception) => {
          //Do something with my error
        }
    })
   
        
}





