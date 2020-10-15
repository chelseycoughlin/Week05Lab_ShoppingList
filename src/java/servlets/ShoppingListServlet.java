/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chels
 */
@WebServlet(name = "ShoppingListServlet", urlPatterns = {"/ShoppingListServlet"})
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        String name = (String) session.getAttribute("user");
        
        if (action != null && action.equals("logout")) {
            session.invalidate();
            request.setAttribute("displayMessage", "Logged out.");
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        } 
        else if (session.getAttribute("user") != null) {
             getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
        } else if (name == null || name == "") {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (request.getSession().getAttribute("user") == null && !action.equals("register")) {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);

            return;
        }
        
        ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
        
        if (items == null)
            items = new ArrayList<String>();
        
        switch (action) {
            case "register": {
                String name = request.getParameter("user");
            
                if (name == null || name.trim().isEmpty()) {
                    request.setAttribute("message", "Name is missing or is empty.");
                    getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                    
                    return;
                } else {
                    request.getSession().setAttribute("user", request.getParameter("user"));
                    
                }
            
                break;
            }
            case "add": {
                String item = request.getParameter("item");
                
                if (item == null || item.trim().isEmpty()) {
                    request.setAttribute("message", "Item cannot be missing or empty.");
                } else {
                    items.add(item);
                    request.setAttribute("info", "Add item to list successfully.");
                }
                
                break;
            }
            
            case "delete": {
                String itemToDelete = request.getParameter("item");
                
                if (itemToDelete == null || itemToDelete.trim().isEmpty()) {
                    request.setAttribute("message", "Item is not selected.");
                } else {
                    int deleted = 0;
                    
                    for (int i = 0; i < items.size(); i++) {
                        String item = items.get(i);
                        
                        if (item.equals(itemToDelete)) {
                            items.remove(i);
                            deleted++;
                        }
                            
                    }
                    
                    if (deleted > 0)
                        request.setAttribute("info", "Deleted " + deleted + " item(s) from list successfully.");
                    else
                        request.setAttribute("message", "No items found to be deleted.");
                }
                
                break;
            }
        }
        
        request.getSession().setAttribute("items", items);
        request.setAttribute("items", items);
        
        getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
    }
}
