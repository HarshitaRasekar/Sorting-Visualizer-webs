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
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String input = request.getParameter("numbers");
        String[] nums = input.split(",");
        int[] array = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            array[i] = Integer.parseInt(nums[i].trim());
        }

        // Save to DB
        NumberDAO.saveInputArray(input);

        List<SortStep> steps = SortingAlgorithm.getBubbleSortSteps(array);
        request.setAttribute("steps", steps);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
