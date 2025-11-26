package model.dto;

public class ReplyDTO {
	private int rid;
	private String content;
	private String writer;
	private int bid;
	
	private String writerName;
	
	private String condition;
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	@Override
	public String toString() {
		return "ReplyDTO [rid=" + rid + ", content=" + content + ", writer=" + writer + ", bid=" + bid + ", writerName="
				+ writerName + ", condition=" + condition + "]";
	}
}
