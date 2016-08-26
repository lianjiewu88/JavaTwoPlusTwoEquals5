package refactingPractice;

class Template{
	private String id;
	private String content;
	public Template(String id){
		this.id = id;
		this.content = id + "-" + "content";
	}
	public String getTemplateFileContent(){
		return this.content;
	}
}


public class AbstractFormDocumentProcessor {

	private String masterTemplate;
	private String frame;
	private final String[] resultHolder = {this.masterTemplate, this.frame};
	private final String[] id = {"Master", "Frame"};
	
	private void getFrameAndMasterTemplate(String masterTemplateId, String frameId) {

		this.masterTemplate = this.findByTemplateId("Master").getTemplateFileContent();
		this.frame = this.findByTemplateId("Frame").getTemplateFileContent();
		System.out.println("Master: " + this.masterTemplate + " frame: " + this.frame);
	}
	
	private void getFrameAndMasterTemplate2() {
		for( int i = 0; i < resultHolder.length; i++){
			resultHolder[i] = this.findByTemplateId(id[i]).getTemplateFileContent();
		}
		System.out.println("Master: " + this.masterTemplate + " frame: " + this.frame);
	}
	private Template findByTemplateId(String id){
		return new Template(id);
	}
	
	public void run(){
		this.getFrameAndMasterTemplate("master", "frame");
		this.getFrameAndMasterTemplate2();
	}
	public static void main(String[] args) {
		AbstractFormDocumentProcessor processor = new AbstractFormDocumentProcessor();
		processor.run();
	}
}
