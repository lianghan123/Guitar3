package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Guitar;
import model.GuitarSpec;
import model.Inventory;

@WebServlet("/AllGuitar")
public class AllGuitar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AllGuitar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();

		// ³õÊ¼»¯inventory
		Inventory inventory = new Inventory();
		inventory.initialize();

		// ¿ÕGuitarSpec
		Map properties = new HashMap();
		GuitarSpec Nullspec = new GuitarSpec(properties);
		// ²éÑ¯Æ¥Åä
        List<Guitar> Guitar = inventory.search(Nullspec);
		if (!Guitar.isEmpty()) {
			for (Iterator<Guitar> i = Guitar.iterator(); i.hasNext();) {
				Guitar guitar = (Guitar) i.next();
				GuitarSpec spec = guitar.getSpec();
				// Ð´Èëjson
				
				properties.put("serialNumber", guitar.getSerialNumber());
				properties.put("price", guitar.getPrice());
				properties.put("builder", spec.getProperty("builder").toString());
				properties.put("model", spec.getProperty("model").toString());
				properties.put("type", spec.getProperty("type").toString());
				properties.put("backWood", spec.getProperty("backWood").toString());
				properties.put("topWood", spec.getProperty("topWood").toString());
				properties.put("StringNum", spec.getProperty("StringNum").toString());
				
			}
		}
		out.print(toString());
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
