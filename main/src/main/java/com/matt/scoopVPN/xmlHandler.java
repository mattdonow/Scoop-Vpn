/*
 * Copyright (c) 2012-2018 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package com.matt.scoopVPN;
import de.blinkt.openvpn.R;
import android.content.Context;
import android.util.Log;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

/**
 * Created by Matt on 2/25/2018.
 */

public class xmlHandler {

    private InputSource input;
    private final XPath xpath;
    final String TAG = "XMLHANDLER";

    public xmlHandler(Context context) {
        xpath = XPathFactory.newInstance().newXPath();
        input = new InputSource(context.getResources().openRawResource(R.raw.airvpn));
    }

    public String[] getServerNamesByCountry(String country) {
        NodeList nodes = getServersByCountry(country);
        String serverNames[]=new String[nodes.getLength()];
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node =nodes.item(i);
            NamedNodeMap nodeMembers = node.getAttributes();
            Node name = nodeMembers.getNamedItem("location");
            String serverName = name.getNodeValue();
            Log.i("NODE SERVER", serverName);
            serverNames[i] = serverName;
        }

        return serverNames;
    }

    public String[] getCountriesList() {
        NodeList nodes = getAllServers();
        String countries[] = new String[nodes.getLength()];
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);
            NamedNodeMap nodeMembers = node.getAttributes();
            Node name = nodeMembers.getNamedItem("country_code");
            String country = name.getNodeValue();
            countries[i] = country;
        }
        Set<String> stringSet = new HashSet<>(Arrays.asList(countries));
        String[] uniqueCountries=stringSet.toArray(new String[0]);

        return uniqueCountries;
    }


    public NodeList getServersByCountry(String country ) {
        String aPath="/eddie/manifest/servers/*[@country_code=\"" + country + "\"]";

        return getXpathNodes(aPath);


    }

    public NodeList getAllServers() {
        String aPath="/eddie/manifest/servers/*";
        return getXpathNodes(aPath);


    }



        private NodeList getXpathNodes (String aPath){
            NodeList nodes = null;
            try {
                nodes = (NodeList) this.xpath.evaluate(aPath, input, XPathConstants.NODESET);
                Log.i("XMLHANDLER", "Number of nodes found: " + nodes.getLength());
                for (int i = 0; i < nodes.getLength(); i++) {
                    Node node = nodes.item(i);
                    NamedNodeMap attr = node.getAttributes();
                    Node attrNode = attr.getNamedItem("name");
                    attrNode.getNodeValue();

                    Log.v(TAG, attrNode.getNodeValue());
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to get Xpath");
                e.printStackTrace();


            }
            return nodes;
        }
        //String path= "/eddie/manifest/servers/*";
        //NodeList nodes = (NodeList) xpath.evaluate(path, input,XPathConstants.NODESET );
    }
