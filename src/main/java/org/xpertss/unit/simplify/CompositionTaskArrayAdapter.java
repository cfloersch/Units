package org.xpertss.unit.simplify;

import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;

/**
 * Package private array element visitor utility for CompositionTask.
 */
final class CompositionTaskArrayAdapter<T> {

  private final T[] array;

  public static <T> CompositionTaskArrayAdapter<T> of(T[] array){
    return new CompositionTaskArrayAdapter<T>(array);
  }

  private CompositionTaskArrayAdapter(T[] array) {
    this.array = array;
  }

  /**
   * For the underlying array visits all sequential pairs of elements.
   * @param visitor
   */
  public void visitSequentialPairs(BiConsumer<T, T> visitor) {
    if(array.length<2) {
      return;
    }
    for(int i=1; i<array.length; ++i) {
      visitor.accept(array[i-1], array[i]);
    }
  }

  /**
   * @param visitor must either return null (meaning no simplification found) or a simplification 
   * @return the number of simplifications that could be found and were applied
   */
  public int visitSequentialPairsAndSimplify(BinaryOperator<T> visitor) {
    if(array.length<2) {
      return 0;
    }
    int simplificationCount = 0;
    for(int i=1;i<array.length;++i) {
      if(array[i-1]==null) {
        continue;
      }
      T simplification = visitor.apply(array[i-1], array[i]);
      if(simplification!=null) {
        array[i-1] = simplification;
        array[i] = null;
        ++simplificationCount;
      }
    }
    return simplificationCount;
  }

  /**
   * @param nullCount since we know this number in advance, we use it to speed up this method
   * @return a new array with {@code nullCount} null-elements removed  
   */
  public T[] removeNulls(int nullCount) {
    final T[] result = Arrays.copyOf(array, array.length-nullCount);
    int j=0;
    for(int i=0;i<array.length;++i) {
      if(array[i]!=null) {
        result[j++] = array[i];	
      }
    }
    return result;
  }

}