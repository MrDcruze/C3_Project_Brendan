import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RestaurantTest {
//    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant = getRestaurantObject();
        Restaurant restaurantMock  = Mockito.spy(restaurant);

        LocalTime checkTime = LocalTime.parse("12:00:00");
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(checkTime);

        assertTrue(restaurantMock.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant restaurant = getRestaurantObject();
        Restaurant restaurantMock  = Mockito.spy(restaurant);

        LocalTime checkTime = LocalTime.parse("09:00:00");
        Mockito.when(restaurantMock.getCurrentTime()).thenReturn(checkTime);

        assertFalse(restaurantMock.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        Restaurant restaurant = getRestaurantObject();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        Restaurant restaurant = getRestaurantObject();

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        Restaurant restaurant = getRestaurantObject();

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

    @Test
    public void get_items_price_should_return_total_sum_when_items_are_selected() {
        Restaurant restaurant = getRestaurantObject();

        assertEquals(388, restaurant.getItemsPrice(restaurant.getMenu()));
    }

    @Test void get_items_price_should_return_zero_when_empty_list_of_items_is_given() {
        Restaurant restaurant = getRestaurantObject();
        List<Item> items = new ArrayList<Item>();

        assertEquals(0, restaurant.getItemsPrice(items));
    }

    @Test void get_items_price_should_return_zero_when_list_of_items_is_null() {
        Restaurant restaurant = getRestaurantObject();

        assertEquals(0, restaurant.getItemsPrice(null));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<Common Methods>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private Restaurant getRestaurantObject() {
        Restaurant restaurant;
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        return restaurant;

    }
}