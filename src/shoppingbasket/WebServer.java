package shoppingbasket;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandler;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.ResourceHandler;
import shoppingbasket.handlers.Basket;
import shoppingbasket.handlers.Books;
import shoppingbasket.handlers.Checkout;
import shoppingbasket.handlers.Order;

public class WebServer
{
  public static void main( String[] args ) throws Exception
  {
    Server server = new Server(8080);

    // Add index Handler
    ResourceHandler resource_handler = new ResourceHandler();
    resource_handler.setWelcomeFiles(new String[]{ "Login.html" });
    resource_handler.setResourceBase("./WebContent");
    ContextHandler context = new ContextHandler("/");
    context.setHandler(resource_handler);

    // Basket Handler in Class
    ContextHandler basket = new ContextHandler("/basket");
    basket.setHandler(new Basket());

    // Checkout Handler in Class
    ContextHandler checkout = new ContextHandler("/checkout");
    checkout.setHandler(new Checkout());

    // Book List
    ContextHandler books = new ContextHandler("/api/books");
    books.setHandler(new Books());

    // Book List
    ContextHandler orders = new ContextHandler("/api/order");
    orders.setHandler(new Order());

    // Set all contexts on Server
    ContextHandlerCollection contexts = new ContextHandlerCollection();
    contexts.setHandlers(new Handler[] { context, basket, checkout, books, orders });
    server.setHandler(contexts);

    server.start();
    server.join();
  }
}