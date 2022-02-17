
from midiutil.MidiFile import MIDIFile
from itertools import islice

	
# Create the MIDIFile Object with 1 track
MyMIDI = MIDIFile(1)

# Tracks are numbered from zero. Times are measured in beats.
track = 0   
time = 0

# Add track name and tempo.
MyMIDI.addTrackName(track,time,"Sample Track")

# Add a note. addNote expects the following information:
track = 0
channel = 0
time = 0

#Read pitch, duration and time from file # Now add the note.

fp = open("test.jarp")
try:
	line=list(islice(fp,3))
	if line:
		filename=line[0].rstrip()
		volume=int(line[1])
		tempo=int(line[2])
		MyMIDI.addTempo(track,time,tempo)
	while True:
		line=list(islice(fp,3)) #islice returns an iterator ,so you convert it to list here.
		if line:                     
			pitch=int(line[0])
			duration=float(line[1])
			time=float(line[2])
		else:
			break
		MyMIDI.addNote(track,channel,pitch,time,duration,volume)
finally:
	fp.close()

	



# And write it to disk.
binfile = open(filename, 'wb')
MyMIDI.writeFile(binfile)
binfile.close()
