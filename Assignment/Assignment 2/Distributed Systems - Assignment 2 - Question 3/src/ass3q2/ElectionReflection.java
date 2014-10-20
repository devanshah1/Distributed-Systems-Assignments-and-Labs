/**
 * Khalil Fazal 100425046
 * Rayhaan Shakeel 100425726
 * Sarim Mahmood 100372299
 * 
 * This class utilizes the reflection library in order to use the Election class as a client proxy class.
 */

package ass3q2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.Remote;

public class ElectionReflection {

  /** Invoke a method from an object with given parameters.
   * 
   * @param o The object that will be used.
   * @param m The method that is to be called.
   * @param arguments The parameters for the method that will be called.
   * @return The object returned by the method.
   */
  public static Object doOperation(final Remote o, final Method m, final Object[] arguments) {
    try {
      return m.invoke(o, arguments);
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
      e.printStackTrace();
    }

    return null;
  }

  public static void main(final String[] args) {
    final ElectionImpl o = new ElectionImpl();
    final Class<? extends Election> c = o.getClass();

    try {
      // Creating a method object in order to gain access to the vote method
      Method method = c.getMethod("vote", new Class[] { String.class, int.class });
      
      // Invoking the vote method with specified parameters
      final Object voteOnceResult = doOperation(o, method, new Object[] { "Bob", 1 });

      System.out.println(voteOnceResult);

      // Invoking the vote method again to show another vote for the same candidate as before
      final Object voteTwiceResult = doOperation(o, method, new Object[] { "Bob", 2 });

      System.out.println(voteTwiceResult);

      // Creating a method object in order to gain access to the result method
      method = c.getMethod("result", new Class[] { String.class });

      // Invoking the result method
      final Result resultResult = (Result) doOperation(o, method, new Object[] { "Bob" });

      System.out.println(resultResult.candidate + " has " + resultResult.votes + " votes.");

    } catch (final NoSuchMethodException e) {
      e.printStackTrace();
    }
  }
}
