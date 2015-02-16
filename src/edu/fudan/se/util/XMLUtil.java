package edu.fudan.se.util;

import edu.fudan.se.crowdservice.kv.Description;
import edu.fudan.se.crowdservice.kv.ImageDisplay;
import edu.fudan.se.crowdservice.kv.KeyValueHolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class XMLUtil {
	public static String obj2XML(ArrayList<KeyValueHolder> keyValueHolders) {
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().newDocument();
			Element root = doc.createElement("Root");
			doc.appendChild(root);
			for (KeyValueHolder holder : keyValueHolders) {
				Element holderElement = doc.createElement(holder.getClass()
						.getSimpleName());
				root.appendChild(holderElement);

				Element key = doc.createElement("Key");
				key.setTextContent(holder.getKey());
				Element value = doc.createElement("Value");
				Object valueObj = holder.getValue();
				String valueString = "";
				if (valueObj instanceof String) {
					valueString = (String) valueObj;
				} else if (valueObj instanceof byte[]) {
					valueString = new Date().getTime() + ".jpg";
					saveByteArray(valueString, (byte[]) valueObj);
				} else if (valueObj instanceof String[]) {
					valueString = "";
					if (((String[]) valueObj).length > 0) {
						for (String s : (String[]) valueObj) {
							valueString += s + ",";
						}
						valueString = valueString.substring(0,
								valueString.length() - 1);
					}
				}
				value.setTextContent(valueString);

				holderElement.appendChild(key);
				holderElement.appendChild(value);
			}
			return doc2String(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<KeyValueHolder> xml2Obj(String template) {
		ByteArrayInputStream bais = new ByteArrayInputStream(
				template.getBytes());
		ArrayList<KeyValueHolder> result = new ArrayList<KeyValueHolder>();
		try {
			Document doc = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder().parse(bais);
			NodeList nodes = doc.getDocumentElement().getChildNodes();

			int i = 0;
			Node node = nodes.item(0);
			if ("Description".equals(node.getNodeName())) {
				String description = node.getTextContent();
				result.add(new Description(description));
				i++;
			}

			for (; i < nodes.getLength(); i++) {
				node = nodes.item(i);
				Class<KeyValueHolder> clazz = (Class<KeyValueHolder>) Class
						.forName("edu.fudan.se.crowdservice.kv."
								+ node.getNodeName());
				NodeList kv = node.getChildNodes();
				String key = kv.item(0).getTextContent();
				String value = kv.item(1).getTextContent();
				KeyValueHolder holder = clazz.getConstructor(String.class,
						String.class).newInstance(key, value);
				if (holder instanceof ImageDisplay) {
					((ImageDisplay) holder).setValue(loadByteArray(value));
				}
				result.add(holder);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private static void saveByteArray(String bytePath, byte[] bytes) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(bytePath);
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fos);
		}
	}

	private static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static byte[] loadByteArray(String value) {
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		byte[] result = null;
		try {
			fis = new FileInputStream(value);
			baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[8 * 1024];
			int size;
			while ((size = fis.read(buffer, 0, buffer.length)) > 0) {
				baos.write(buffer, 0, size);
			}
			result = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis);
			close(baos);
		}
		return result;
	}

	private static String doc2String(Document doc) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		Writer out = new StringWriter();
		tf.transform(new DOMSource(doc), new StreamResult(out));
		return out.toString();
	}
}
