package prime;

import android.annotation.SuppressLint;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Computes several algorithms using Prime Factors.
 * Includes Lowest Common Multiple, Greatest Common Divisor.
 * @author Philip Bui
 *
 */
@SuppressLint("UseSparseArrays")
public class PrimeFactor {
	/**
	 * Computes a List of Prime Factors for given number.
	 * @param Number
	 * @return ArrayList of Prime Factors.
	 */
	public ArrayList<Integer> listOfPrimeFactors(long number) {
    	ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();
        for (int i = 2; i <= number; i++) {
            if (number % i == 0) {
            	listOfPrimes.add(i);
                number /= i;
                i--;
            }
        }
        return listOfPrimes;
    }
	/**
	 * Computes Lowest Common Multiplier with given numbers.
	 * @param ArrayList with at least two Numbers.
	 * @return Lowest Common Multiplier
	 */
	public int primeLCM(ArrayList<Integer> numbers) {
    	if (numbers.size() <= 1)
    		throw new IllegalArgumentException("Need more than 2 numbers");
    	
    	Map<Integer, Integer> primeMap = new HashMap<Integer, Integer>();
    	for (int i = 0; i < numbers.size(); i++) {
    		ArrayList<Integer> listOfPrimes = listOfPrimeFactors(numbers.get(i));
    		System.out.println(numbers.get(i) + " = " + listOfPrimes);
    		for (int j = 0; j < listOfPrimes.size(); j++)
    		{
    			int key = listOfPrimes.get(j);
    			if (primeMap.containsKey(key)) {
    				int freq = Collections.frequency(listOfPrimes, key);
    				if (freq > primeMap.get(key)) {
    					primeMap.put(key, freq);
    				}
    			}
    			else {
    				primeMap.put(key, 1);
    			}
    		}
    	}
    	int lcm = 0;
    	for (Map.Entry<Integer, Integer> entry : primeMap.entrySet()) {
    		int key = entry.getKey();
    		int value = entry.getValue();
    		int primeMultiply = key;
    		for (int i = 0; i < value; i++) {
	    		if (lcm > 0) {
	    			System.out.println(lcm + "*" + primeMultiply);
	    			lcm *= primeMultiply;
	    		}
	    		else
	    			lcm += primeMultiply;
    		}
    	}
    	return lcm;
    }
    /**
     * Computes Greatest Common Divisor with given numbers.
     * @param ArrayList with at least two Numbers.
     * @return Greatest Common Divisor
     */
    public int primeGCD(ArrayList<Integer> numbers) {
    	if (numbers.size() <= 1) {
    		throw new IllegalArgumentException("Need more than 2 numbers");
    	}
    	ArrayList<Integer> commonPrimes = listOfPrimeFactors(numbers.get(0));
    	for (int i = 1; i < numbers.size(); i++) {
    		commonPrimes.retainAll(listOfPrimeFactors(numbers.get(i)));
    	}
    	int gcd = commonPrimes.get(0);
    	for (int i = 1; i < commonPrimes.size(); i++) {
    		gcd *= commonPrimes.get(i);
    	}
    	return gcd;
    }
}
