import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * From the pool of academic courses, the university offers different courses each semester. 
 * In this class, we store all the offered courses in a semester. 
 * @author Labib Rahman
 * */
public class CurrentOfferedCourse implements Iterable<Course> {
	private List<Course> cList;

	public CurrentOfferedCourse() {
		cList = new ArrayList<>();
	}
	
	/**
	 * @param Course object to add the ArrayList. 
	 * */
	
	public void addCourse(Course course) {
		cList.add(course);
	}

	/**
	 *  @return Course object if it is offered in a semester. 
	 *  */
	
	public Course getCourse(Course course) {
		if (cList.isEmpty()) {
			System.out.println("No courses have been added!");
			throw new NoSuchElementException();

		} else {
			for (Course c : cList) {
				if ((c.getCourseTitle()).equals(course.getCourseTitle())
						&& (c.getCourseId()).equals(course.getCourseId())) {
					return c;
				}
			}
		}
		System.out.println("Course not found!");
		throw new NoSuchElementException();
	}

	/**
	 * @return An ArrayList<Course> containing all the offered courses in a
	 * semester.
	 */
	
	public List<Course> getCourseList() {
		return cList;
	}

	public boolean isEmpty() {
		return cList.isEmpty();
	}

	@Override

	public Iterator<Course> iterator() {

		return cList.iterator();
	}

}
