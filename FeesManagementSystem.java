
import java.util.ArrayList;
import java.util.List;

class Student {
    private String studentId;
    private String name;
    // Add more student details as needed

    public Student(String studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    // Getter and setter methods
       @Override
        public String toString(){
        return "Student{" +
                "studentId=" + studentId +
                ", name=" + name + 
                "}";
	   }
}

class FeeStructure {
    private String courseId;
    private double tuitionFee;
    private double lateFee;

    public FeeStructure(String courseId, double tuitionFee, double lateFee) {
        this.courseId = courseId;
        this.tuitionFee = tuitionFee;
        this.lateFee = lateFee;
    }

    // Getter methods
	public double getTuitionFee(){
		return tuitionFee;
	}

	public double getLateFee(){
		return lateFee;
	}
       @Override
		   public String toString(){
        return "FeeStructure{" +
                "courseId=" + courseId + 
                ", tuitionFee=" + tuitionFee +
                ", lateFee=" + lateFee +
                "}";
	   }
    
	}

class Invoice {
    private String invoiceId;
    private Student student;
    private FeeStructure feeStructure;
    private double totalAmount;
    private boolean paid;

    public Invoice(String invoiceId, Student student, FeeStructure feeStructure) {
        this.invoiceId = invoiceId;
        this.student = student;
        this.feeStructure = feeStructure;
        this.totalAmount = feeStructure.getTuitionFee();
        this.paid = false;
    }

    // Getter and setter methods
	public String getInvoiceId(){
		return invoiceId;
	}

    public void addLateFee() {
        if (!paid) {
            totalAmount += feeStructure.getLateFee();
        }
    }

    public void markAsPaid() {
        paid = true;
    }
      
     @Override
		 public String toString(){
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", student=" + student +
                ", feeStructure=" + feeStructure +
                ", totalAmount=" + totalAmount +
                ", paid=" + paid +
                "}";
	 }
    
}

 
public class FeesManagementSystem
	{
	
    private List<Student> students;
    private List<FeeStructure> feeStructures;
    private List<Invoice> invoices;

    public FeesManagementSystem() {
        this.students = new ArrayList<>();
        this.feeStructures = new ArrayList<>();
        this.invoices = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addFeeStructure(FeeStructure feeStructure) {
        feeStructures.add(feeStructure);
    }

    public void generateInvoice(Student student, FeeStructure feeStructure) {
        String invoiceId = "INV" + (invoices.size() + 1);
        Invoice invoice = new Invoice(invoiceId, student, feeStructure);
        invoices.add(invoice);
        System.out.println("Invoice generated: " + invoice);
    }

    public void processPayment(String invoiceId) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId().equals(invoiceId)) {
                invoice.markAsPaid();
                System.out.println("Payment processed for Invoice " + invoiceId);
                return;
            }
        }
        System.out.println("Invoice not found: " + invoiceId);
    }

    // Add more methods as needed for additional functionalities

    public static void main(String[] args) {
        FeesManagementSystem system = new FeesManagementSystem();

        Student student1 = new Student("S1", "John Doe");
        system.addStudent(student1);

        FeeStructure feeStructure1 = new FeeStructure("C1", 1000.0, 50.0);
        system.addFeeStructure(feeStructure1);

        system.generateInvoice(student1, feeStructure1);

        // Simulate late payment
        Invoice invoice1 = system.invoices.get(0);
        invoice1.addLateFee();

        // Simulate payment processing
        system.processPayment(invoice1.getInvoiceId());
    }
}



