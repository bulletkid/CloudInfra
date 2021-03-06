package com.manuanand.cloudinfra;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@Controller	// This means that this class is a Controller
@RequestMapping(path="/node") // This means URL's start with /node (after Application path)
public class NodeController {
	@Autowired // This means to get the bean called nodeRepository
			   // Which is auto-generated by Spring, we will use it to handle the data
	private NodeRepository nodeRepository;
	
	///
	// Node Repository
	///
	@PostMapping(path="/add") // Map ONLY POST Requests
	public @ResponseBody Node addNode (
			@RequestParam String partNumber, @RequestParam String nodeVendorString,
			@RequestParam String locationString, @RequestParam Integer totalCores) {
		
		// Validation for Node Vendor
		NodeVendor vendor = NodeVendor.Unknown;
		if (nodeVendorString.equalsIgnoreCase("Intel")) {
			vendor = NodeVendor.Intel;
		} else if (nodeVendorString.equalsIgnoreCase("Amd")) {
			vendor = NodeVendor.AMD;
		} else if (nodeVendorString.equalsIgnoreCase("Arm")) {
			vendor = NodeVendor.ARM;
		}

		if (vendor == NodeVendor.Unknown) {
			throw new ResponseStatusException(
				  HttpStatus.BAD_REQUEST, "Vendor " + nodeVendorString + " is invalid." +
				  "Please specify vendor as either Intel, AMD or ARM.");
		}
		
		// Validation for location
		DataCenterLocation location = DataCenterLocation.Unknown;
		if (locationString.equalsIgnoreCase("DEL")) {
			location = DataCenterLocation.DEL;
		} else if (locationString.equalsIgnoreCase("BOM")) {
			location = DataCenterLocation.BOM;
		} else if (locationString.equalsIgnoreCase("SFO")) {
			location = DataCenterLocation.SFO;
		} else if (locationString.equalsIgnoreCase("DUB")) {
			location = DataCenterLocation.DUB;
		}

		if (location == DataCenterLocation.Unknown) {
			throw new ResponseStatusException(
				  HttpStatus.BAD_REQUEST, "Location " + locationString + " is invalid." +
				  "Valid values (DEL, BOM, SFO, DUB).");
		}

		Node newNode = new Node();
		newNode.setPartNumber(partNumber);
		newNode.setVendor(vendor);
		newNode.setLocation(location);
		newNode.setTotalCores(totalCores);
		newNode.setUsedCores(0);
		
		nodeRepository.save(newNode);
		
		return newNode;
	}
	
	@GetMapping(path="/")
	public @ResponseBody Iterable<Node> getAllNodes() {
		
		// This returns a JSON or XML with the users
		return nodeRepository.findAll();
	}

	@GetMapping(path="/{id}")
	public @ResponseBody Node getSpecificNode(@PathVariable String id) {
		
		Integer nodeId = null;
		try {
			nodeId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return null;
		}

		Optional<Node> node = nodeRepository.findById(nodeId);
		if (!node.isEmpty()) {
			return node.get();
		} 

		return null;
	}

	@GetMapping(path="/deleteAll")
	public @ResponseBody void deleteAllNodes() {
		
		// This returns a JSON or XML with the users
		nodeRepository.deleteAll();
	}
}