/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilities;

import java.util.ArrayList;
import java.util.List;
 
 
public class Util{
 
  public static <E> List<E> toList(Iterable<E> iterable) {
    if(iterable instanceof List) {
      return (List<E>) iterable;
    }
    ArrayList<E> list = new ArrayList<E>();
    if(iterable != null) {
      for(E e: iterable) {
        list.add(e);
      }
    }
    return list;
  }
}