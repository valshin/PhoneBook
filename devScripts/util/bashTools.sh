#!/bin/bash
export COLOR_NC='\e[0m' # No Color
export COLOR_WHITE='\e[1;37m'
export COLOR_BLACK='\e[0;30m'
export COLOR_BLUE='\e[0;34m'
export COLOR_LIGHT_BLUE='\e[1;34m'
export COLOR_GREEN='\e[0;32m'
export COLOR_LIGHT_GREEN='\e[1;32m'
export COLOR_CYAN='\e[0;36m'
export COLOR_LIGHT_CYAN='\e[1;36m'
export COLOR_RED='\e[0;31m'
export COLOR_LIGHT_RED='\e[1;31m'
export COLOR_PURPLE='\e[0;35m'
export COLOR_LIGHT_PURPLE='\e[1;35m'
export COLOR_BROWN='\e[0;33m'
export COLOR_YELLOW='\e[1;33m'
export COLOR_GRAY='\e[0;30m'
export COLOR_LIGHT_GRAY='\e[0;37m'

function psExists {
	local PSMASK=$1
	ps ax | grep "$PSMASK" | grep -vo grep
	return $?
}

function jpsExists {
	local PSMASK=$1
	jps | grep "$PSMASK" | grep -vo grep
	return $?
}

function killPs {
	local PSMASK=$1
	local pid=$(ps aux | grep "$PSMASK" | grep -v grep | awk '{print $2}')
	kill $pid
}

function killjPs {
	local PSMASK=$1
	local pid=$(jps | grep "$PSMASK" | grep -v grep | awk '{print $1}')
	kill $pid
}

function sayError {
    printf "***********************************************************************\n"
    printf "$COLOR_RED$1$COLOR_NC\n"
    printf "***********************************************************************\n"
}

function sayOk {
    printf "***********************************************************************\n"
    printf "$COLOR_GREEN$1$COLOR_NC\n"
    printf "***********************************************************************\n"
}
