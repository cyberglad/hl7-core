hl7-core
========

HL7 Parser
It is a simple parser used to manipulate HL7 v2 messages (replace the field value).

Say you want to set PID2 to "TEST":

HL7Message hl7Message = new HL7Message(load(testFile));
hl7Message.setField("OBR", 3, "TEST");

to retrieve the value:

hl7Message.getField("OBR", 3);
