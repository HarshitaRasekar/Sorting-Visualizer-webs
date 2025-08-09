package com.example.servlet;

import java.io.IOException;
import java.util.List;

import com.example.dao.NumberDAO;
import com.example.model.SortStep;
import com.example.sort.SortingAlgorithm;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/sort")
public class SortingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String input = request.getParameter("numbers");
        String algorithm = request.getParameter("algorithm"); // Algorithm ka naam le raha hai

        String[] nums = input.split(",");
        int[] array = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            array[i] = Integer.parseInt(nums[i].trim());
        }

        // Save numbers to DB
        NumberDAO.saveInputArray(input);

        // Select sorting algorithm
        List<SortStep> steps;
        switch (algorithm) {
            case "Selection":
                steps = SortingAlgorithm.getSelectionSortSteps(array);
                break;
            case "Insertion":
                steps = SortingAlgorithm.getInsertionSortSteps(array);
                break;
            default: // Bubble sort by default
                steps = SortingAlgorithm.getBubbleSortSteps(array);
                break;
        }

        // Pass steps and algorithm name to JSP
        request.setAttribute("steps", steps);
        request.setAttribute("algorithm", algorithm);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
