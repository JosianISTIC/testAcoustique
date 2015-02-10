package my.test.vca;

import java.util.Observer;
import java.util.Set;

/**
 * An interface to overcome the lack of specification on this in the API.
 * @inv
 *     getObservers(p) != nulls
 */
public interface ObservableModel {

    // request

    /**
     * The number of observers registered with this model.
     * @pre
     *  property != null
     */
    Set<Observer> getObservers(String property);

    // command

    /**
     * Register an observer on this model if it is not already there.
     * @pre
     *     o != null
     *     property != null
     *     !getObservers(property).contains(o)
     * @post
     *     getObservers(property).contains(o)
     */
    void addObserver(String property, Observer o);

    /**
     * Register an observer on this model if it is not already there.
     * @pre
     *     o != null
     *     property != null
     *     getObservers(property).contains(o)
     * @post
     *     !getObservers(property).contains(o)
     */
    void removeObserver(String property, Observer o);


    /**
     * Notify all observers.
     */
    void notifyObservers();
}

