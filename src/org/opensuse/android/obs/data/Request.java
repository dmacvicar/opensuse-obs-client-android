package org.opensuse.android.obs.data;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import android.util.Log;

@Root(strict=false)
public class Request {
	@Attribute
	private String id;
	@Element(required=false)
	private String description;
	@Element(name="state")
	private State state;
	
	@Element(name="submit", required=false)	
	private Submit submit;
	
	@Element(name="action", required=false)
	private Action action;
	
	public String getId() {
		return id;
	}
	
	public String getDescription() {
		return description;	
	}
	
	public State getState() {
		return state;
	}
	
	public Action getAction() {		
		Log.i("REQUESTACTION", ": " + action + "-" + submit);		
		if (action == null && submit != null) {
			Action tmp_action = new Action();
			tmp_action.setType("submit");
			tmp_action.setTarget(submit.getTarget());
			tmp_action.setSource(submit.getSource());
			return tmp_action;
		}
		return action;
	}
	
	@Root(strict=false)
	public static class State {		
		@Attribute
		private String name;
		@Attribute
		private String who;
		@Attribute
		private String when;
		
		public String getName() {
			return name;
		}
		
		public String getWho() {
			return who;
		}
		
		public String getWhen() {
			return when;
		}
	}
	
	@Root(name="submit", strict=false)
	public static class Submit {
		
		@Element(name="source")
		private Action.Source source;
		@Element(name="target")
		private Action.Target target;

		public Action.Source getSource() {
			return source;
		}
		public Action.Target getTarget() {
			return target;
		}	
	}
	
	@Root(strict=false)
	public static class Action {
				
		@Attribute
		private String type;
		@Element
		private Source source;
		@Element
		private Target target;

		public Source getSource() {
			return source;
		}
		public Target getTarget() {
			return target;
		}
		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}
		public void setSource(Source source) {
			this.source = source;
		}
		public void setTarget(Target target) {
			this.target = target;
		}

		@Root(strict=false, name="source")
		public static class Source {
			
			@Attribute
			private String project;
			@Attribute(name="package")
			private String _package;
			@Attribute(required=false)
			private String rev;
			
			public String getProject() {
				return project;
			}
			public String getPackage() {
				return _package;
			}
			public String getRev() {
				return rev;
			}	
		}
		
		@Root(strict=false, name="target")
		public static class Target {	
			@Attribute
			private String project;
			@Attribute(name="package")
			private String _package;
			
			public String getProject() {
				return project;
			}
			public String getPackage() {
				return _package;
			}	
		}

	}
	
}
