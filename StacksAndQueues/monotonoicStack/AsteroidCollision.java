package StacksAndQueues.monotonoicStack;

import java.util.ArrayList;
import java.util.List;

/*

  you are given an array of integers asteroids representing asteroids in a row.
    For each asteroid, the absolute value represents its size, 
    and the sign represents its direction (positive meaning right, negative meaning left).
    Each asteroid moves at the same speed.
    Find out the state of the asteroids after all collisions.

*/

class Soultion{

    /*
        When two asteroids are moving in opposite direction, they collide. There can be two cases of
        opposite moving asteroids:
        1. moving apart from each other - no collision.
        2. moving towards each other - collision occurs.

        Now in order to perform the collisions, only the left moving asteroids must be observed as all the earlier 
        asteriods will be moving towards the right direction. 
        For every moving asteriod, we must first consider the asteriod that was found last moving towards the right because it will
        be the asteroid undergoing collision first. This gives an idea of using
        stack data structure to store its elements in LIFO fashion.
    */
}

public class AsteroidCollision {
    public static int[] AsteroidCollision(int[] asteriods){
        int n = asteriods.length;
        List<Integer> st = new ArrayList<>();

        for(int i = 0; i < n; i++){
            int curr = asteriods[i];
            if(curr > 0){
                st.add(curr);
                continue;
            }

            while(!st.isEmpty() && st.get(st.size() - 1) > 0 
                    && st.get(st.size() - 1) < Math.abs(curr)){
                st.remove(st.size() - 1);
            }

            /*
                if there is right moving asteroid which is of same size as current left moving asteroid,
                then both will explode each other and we don't need to add current asteroid to stack. 
            */
            if(!st.isEmpty() && st.get(st.size() - 1) == Math.abs(curr)){
                st.remove(st.size() - 1);
            } 
            /*
                otherwise if there is no left moving asteroid, the right moving asteroid will not be destroyed
                and we can safely add the current left moving asteroid to stack.
            */
            else if(st.isEmpty() || st.get(st.size() - 1) < 0){
                st.add(curr);
            }
        }

        // int[] res = new int[st.size()];
        // for(int i = 0; i < st.size(); i++){
        //     res[i] = st.get(i);
        // }

        return st.stream().mapToInt(Integer::intValue).toArray();
        
    }
}
