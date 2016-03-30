#!/usr/bin/python

import re
import sys
import os
from os import walk
from os import path


if len(sys.argv) != 4:
	print "Usage: " + sys.argv[0] + " <module name> <module description> <email address>"
	print "For example: python " + sys.argv[0] + " testmodule \"This is a testing module\" example@example.com"
	exit(1)

new_module_name = str(sys.argv[1])
module_description = str(sys.argv[2])
module_email = str(sys.argv[3])


# Expand cwd:
cwd = os.path.dirname(os.path.realpath(__file__))

# Directory to start from (replace with '.' to work from the relative current working directory):
base_dir = path.join(cwd, new_module_name)

# Files to rename:
rename_files = {
	'moduleIdTagModule.java': new_module_name + 'Module.java',
	'moduleIdTag.xml':        new_module_name + '.xml',
	'moduleIdTag.feature':    new_module_name + '.feature',
};

# Directories to rename:
rename_dirs = {
	'moduleIdTag': new_module_name,
};

# Tag name to match against:
template_module_name = 'moduleIdTag'

# Regular expression to match files to edit:
file_pattern = '(\.java|\.feature|\.properties|\.xml|\.project|\.classpath)$'

# Open a file, replace all matching lines and write back over it
# Warning: Destructive, doesn't backup and probably would break binary files
def fix_file (filename, properties_file = False):
	fh = open(filename, 'r')
	# Read contents in to array to make it easier to match regex:
	contents = tuple(fh)
	fh.close()

	# Buffer to store new file contents (more efficient to just write-out, but meh!)
	new_contents = []

	for line in contents:
		# Add any other regexes here:
		line = re.sub(template_module_name, new_module_name, line)
		if (properties_file == True):
			line = re.sub('description=.*$', 'description=' + module_description, line)
			line = re.sub('name=.*$', 'name=' + new_module_name, line)
			line = re.sub('contact=.*$', 'contact=' + module_email, line)
		new_contents.append(line)

	# Write back changes:
	fh = open(filename, 'w')
	fh.write(''.join(new_contents))
	fh.close()

# Fix the pod definition xml file specifically
def fix_pod_definition (filename):
	pod_new_name = "[" + new_module_name[0].lower() + new_module_name[0].upper() + "]" + new_module_name[1:]
	original_line = '<device-model vendor="[mM]oduleIdTag" version=".*" model="FAS.*|.*Cluster.*|.*OnCommand.*|.*DFM.*" />'
	new_line = '<device-model vendor="'+ pod_new_name +'" version=".*" model="FAS.*|.*Cluster.*|.*OnCommand.*|.*DFM.*" />'
	fh = open(filename, 'r')
	# Read contents in to array to make it easier to match regex:
	contents = tuple(fh)
	fh.close()
	# Buffer to store new file contents (more efficient to just write-out, but meh!)
	new_contents = []
	for line in contents:
		line = re.sub(original_line, new_line, line)
		new_contents.append(line)
	# Write back changes:
	fh = open(filename, 'w')
	fh.write(''.join(new_contents))
	fh.close()

# Fix the classpath file specifically
def fix_classpath (filename):
	original_template_str = '/Users/rwhitear/Documents/Eclipse/moduleIdTag'
	fh = open(filename, 'r')
	# Read contents in to array to make it easier to match regex:
	contents = tuple(fh)
	fh.close()
	# Buffer to store new file contents (more efficient to just write-out, but meh!)
	new_contents = []
	for line in contents:
		# Add any other regexes here:
		line = re.sub(original_template_str, base_dir, line)
		new_contents.append(line)
	# Write back changes:
	fh = open(filename, 'w')
	fh.write(''.join(new_contents))
	fh.close()

# Manually edit the folder name:
base_path = path.join(cwd, 'moduleIdTag')
if (os.path.isdir(base_path)):
	new_path = path.join(cwd, new_module_name)
	os.rename(base_path, new_path)


# Fix the class path file:
classpath_file = path.join(cwd,new_module_name,'.classpath')
fix_classpath(classpath_file)


for (dirpath, dirnames, filenames) in walk(base_dir):
	for filename in filenames:
		# Check if it matches an 'interesting' file (useful to avoid opening binary files!)
		if (re.search(file_pattern, filename)):
			full_path = path.join(dirpath,filename)
			fix_file(full_path, filename == 'module.properties')
		# Also see if it's in the rename dictionary:
		if (filename in rename_files.keys()):
			old_file_path = path.join(dirpath,filename)
			new_file_path = path.join(dirpath, rename_files[filename])
			os.rename(old_file_path, new_file_path)

# Do the directories in a separate run after the files, to ensure the tree is stable:
for (dirpath, dirnames, filenames) in walk(base_dir):
	for dirname in dirnames:
		# Check if it's in the rename dictionary:
		if (dirname in rename_dirs.keys()):
			old_dir_path = path.join(dirpath, dirname)
			new_dir_path = path.join(dirpath, rename_dirs[dirname])
			os.rename(old_dir_path, new_dir_path)


# Fix the poddefinition xml file:
xml_file = path.join(base_dir, 'poddefinition', new_module_name + ".xml")
fix_pod_definition(xml_file)